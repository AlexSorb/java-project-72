@import gg.jte.Content
@import hexlet.code.dto.BasePage
@import hexlet.code.util.NamedRoutes

@param Content content
@param BasePage page = null

<!-- layout -->
<!doctype html>
<html lang="ru">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Анализатор страниц</title>

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" 
        rel="stylesheet" ntegrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" 
        crossorigin="anonymous">

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" 
        integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" 
        crossorigin="anonymous"></script>

    </head>
    <body>
        <nav class="navbar navbar-expand-lg bg-body-tertiary", data-bs-theme="dark">
            <div class="container-fluid">
              <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                  <a class="nav-link active" aria-current="page" href="/">Анализатор страниц</a>
                  <a class="nav-link" href="/urls">Сайты</a>
                </div>
              </div>
            </div>
          </nav>

        <main class ="container">
            <!-- flash -->
            @if(page != null && page.getFlash() != null)
                <div class="alert alert-success" role="alert">
                    ${page.getFlash()}
                </div>
            @endif
            
            <!-- content -->
            ${content}
        </main>
    </body>
</html>