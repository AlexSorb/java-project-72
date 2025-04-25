@import hexlet.code.dto.url.UrlsPage

@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
         <div class="index_block">
            @for(var url : page.getUrlList())
                <h4>${url.getId()} ${url.getName()}</h4>
            @endfor
        </div>
    `,
)